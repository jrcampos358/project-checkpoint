package com.jrc.checkpoint.model;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;


@Entity
//defining class name as Table name
@Table
public class Checkpoint {
    // Define id as primary key
    @Id
    @Column
    @GeneratedValue
    private UUID id;
    @Column
    private String name;
    @Column
    private UUID projectId;

    @JsonInclude()
    @Transient
    private int complPercentage;

    @OneToMany(targetEntity = Task.class, mappedBy = "checkpointId", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<Task>();

    public Checkpoint() {
    }

    public Checkpoint(String cname, UUID projId) {
        name = cname;
        projectId = projId;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getComplPercentage() {
        return complPercentage;
    }

    public void setComplPercentage(int percentage) {
        complPercentage = percentage;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public UUID getProjectId() {
        return projectId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + this.complPercentage;
        hash = 79 * hash + Objects.hashCode(this.projectId);
        return hash;
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
        final Checkpoint other = (Checkpoint) obj;
        if (this.name != other.name) {
            return false;
        }
        if (this.projectId != other.projectId) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Id{id=").append(id).append(", name=")
                .append(name).append(", complPercentage=")
                .append(complPercentage).append(", projectId=")
                .append(projectId).append("}");

        return builder.toString();
    }
}