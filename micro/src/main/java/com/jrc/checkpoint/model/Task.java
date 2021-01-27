package com.jrc.checkpoint.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table
public class Task {
    // Define id as primary key
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String text;
    @Column
    private int complPercentage;
    @Column
    private UUID checkpointId;

    public Task() {
    }

    public Task(String ptext, int pcomplPercentage, UUID pcheckpointId) {
        text = ptext;
        complPercentage = pcomplPercentage;
        checkpointId = pcheckpointId;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getComplPercentage() {
        return complPercentage;
    }

    public UUID getCheckpointId() {
        return checkpointId;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        if (this.text != other.text) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Id{id=").append(id).append(", text=")
                .append(text).append(", complPercentage=")
                .append(complPercentage).append("}");

        return builder.toString();
    }

}