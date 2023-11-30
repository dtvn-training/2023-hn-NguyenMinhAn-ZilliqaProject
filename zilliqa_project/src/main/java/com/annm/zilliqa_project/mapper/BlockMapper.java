package com.annm.zilliqa_project.mapper;

import com.annm.zilliqa_project.entity.Blocks;
import com.google.cloud.bigquery.FieldValueList;
import org.springframework.core.convert.converter.Converter;

public class BlockMapper implements Converter<FieldValueList, Blocks> {
    @Override
    public Blocks convert(FieldValueList row) {

        return null;
    }

    @Override
    public <U> Converter<FieldValueList, U> andThen(Converter<? super Blocks, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
