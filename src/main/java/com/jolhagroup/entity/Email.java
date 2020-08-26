package com.jolhagroup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Email")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email{

    @Id
    public String raw;

    @Column
    public String domain;

    @Column
    public String owner;

}
