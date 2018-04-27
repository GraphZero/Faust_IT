package com.faust.intership.common.infastructure;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@ToString
@Getter
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Version
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int version;

    protected final LocalDate creationDate;

    public AbstractEntity() {
        creationDate = LocalDate.now();
    }

    @Override
    abstract public int hashCode();

    @Override
    abstract public boolean equals(Object obj);

}