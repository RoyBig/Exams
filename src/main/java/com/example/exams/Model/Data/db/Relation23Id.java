package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class Relation23Id implements Serializable {
    private static final long serialVersionUID = -3265220597071189018L;
    private String groupClassid;

    private String egzaminatorEgzaminatorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation23Id entity = (Relation23Id) o;
        return Objects.equals(this.egzaminatorEgzaminatorId, entity.egzaminatorEgzaminatorId) &&
                Objects.equals(this.groupClassid, entity.groupClassid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(egzaminatorEgzaminatorId, groupClassid);
    }
}