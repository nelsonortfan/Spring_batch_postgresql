package com.example.postgresql.batch.demo.task;

import com.example.postgresql.batch.demo.domain.Book;
import com.example.postgresql.batch.demo.repository.BookRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleTask implements Tasklet {

    @Autowired
    BookRepository bookRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Iniciando mi job Nelson con Postgresql");
        System.out.println("------");
        System.out.println("Agregando un libro");
        Book book = new Book();
        book.setTitle("El señor de los anillos");
        book.setAuthor("J. R. Tolkien");
        bookRepository.save(book);
        System.out.println("Guardando el libro en la BD");
        System.out.println("------");
        System.out.println("Finalizando mi job Nelson con Postgresql");
        return RepeatStatus.FINISHED;
    }
}
