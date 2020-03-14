package com.techart.incubator.parser;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class DefaultParserTest {

    @Test
    public void getContent() throws IOException {
        Parser parser = new DefaultParser();
        File testFile = new File("src/test/resurces/fileToTest.txt");
        String result = parser.getContent(testFile);
        assertEquals(result, "result is correct!");
    }

    // По хорошему, делать нужно так:
    // Создать класс TestResources с методом createTestFile(content, filename) и dropAll()
    // Метод createTestFile будет проверять существует ли такой файл и, если не сушесвтует,
    // создавать, занося в специальный буфер,
    // а метод dropAll будет удалять в конце теста все файлы из буфера.
    // dropAll() вызывается в @After методе

    // в случае saveContent() метода для FileWriterContent в TestResources добавляется метод
    // checkIfExists(File file)
    // Он проверит существует ли такой файл, и если нет, то внесет в буфер, чтобы после окончания теста
    // удалить его результаты

}