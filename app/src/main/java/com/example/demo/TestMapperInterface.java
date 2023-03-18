package com.example.demo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TestMapperInterface {
    TestMapperInterface INSTANCE = Mappers.getMapper(TestMapperInterface.class);

    TestOutput toTestOutput(TestInput testInput);
}
