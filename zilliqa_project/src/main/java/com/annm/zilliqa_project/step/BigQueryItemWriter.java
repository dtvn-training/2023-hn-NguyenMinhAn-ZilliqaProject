package com.annm.zilliqa_project.step;


import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.repository.BlockRepository;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public class BigQueryItemWriter<T> extends RepositoryItemWriter<T> {
    public RepositoryItemWriter<T> bigQueryToDatabaseWriter(JpaRepository<T, Integer> repository){
        RepositoryItemWriter<T> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }

}
