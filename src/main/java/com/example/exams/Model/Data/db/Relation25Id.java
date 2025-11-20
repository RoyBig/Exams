package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class Relation25Id implements Serializable {
    private static final long serialVersionUID = 5197636945612056163L;
    private String subjectSubjectid;

    private String groupClassid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation25Id entity = (Relation25Id) o;
        return Objects.equals(this.groupClassid, entity.groupClassid) &&
                Objects.equals(this.subjectSubjectid, entity.subjectSubjectid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupClassid, subjectSubjectid);
    }
}