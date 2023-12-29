package com.annm.zilliqa_project.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.adapter.AbstractMethodInvokingDelegator;
import org.springframework.batch.item.adapter.DynamicMethodInvocationException;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MethodInvoker;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

public class RepositoryItemWriterCustom<T> extends RepositoryItemWriter<T> {

    protected static final Log logger = LogFactory.getLog(RepositoryItemWriter.class);
    private CustomRepository<T, ?> repository;
    private String methodName;

    public RepositoryItemWriterCustom() {
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setRepository(CustomRepository<T, ?> repository) {
        this.repository = repository;
    }

    public void write(List<? extends T> items) throws Exception {
        if (!CollectionUtils.isEmpty(items)) {
            this.doWrite(items);
        }

    }

    protected void doWrite(List<? extends T> items) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Writing to the repository with " + items.size() + " items.");
        }

        if (this.methodName == null) {
            this.repository.saveAll(items);
            List<? extends T> list = this.repository.findAll();
            for (T item : list){
                this.repository.refresh(item);
            }
        } else {
            MethodInvoker invoker = this.createMethodInvoker(this.repository, this.methodName);
            Iterator var3 = items.iterator();

            while(var3.hasNext()) {
                T object = (T) var3.next();
                invoker.setArguments(new Object[]{object});
                this.doInvoke(invoker);
            }

        }
    }

    public void afterPropertiesSet() throws Exception {
        Assert.state(this.repository != null, "A CrudRepository implementation is required");
        if (this.methodName != null) {
            Assert.hasText(this.methodName, "methodName must not be empty.");
        } else {
            logger.debug("No method name provided, CrudRepository.saveAll will be used.");
        }

    }

    private Object doInvoke(MethodInvoker invoker) throws Exception {
        try {
            invoker.prepare();
        } catch (ClassNotFoundException var3) {
            throw new DynamicMethodInvocationException(var3);
        } catch (NoSuchMethodException var4) {
            throw new DynamicMethodInvocationException(var4);
        }

        try {
            return invoker.invoke();
        } catch (InvocationTargetException var5) {
            if (var5.getCause() instanceof Exception) {
                throw (Exception)var5.getCause();
            } else {
                throw new AbstractMethodInvokingDelegator.InvocationTargetThrowableWrapper(var5.getCause());
            }
        } catch (IllegalAccessException var6) {
            throw new DynamicMethodInvocationException(var6);
        }
    }

    private MethodInvoker createMethodInvoker(Object targetObject, String targetMethod) {
        MethodInvoker invoker = new MethodInvoker();
        invoker.setTargetObject(targetObject);
        invoker.setTargetMethod(targetMethod);
        return invoker;
    }
}
