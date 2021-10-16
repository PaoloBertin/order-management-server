package eu.opensource.ordermanagement.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Table(name = "categories")
@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Category(){

    }

    public Category(String name){

        this.name= name;
    }

    public Category(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
