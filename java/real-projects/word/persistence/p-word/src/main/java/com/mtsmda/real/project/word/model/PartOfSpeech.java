package com.mtsmda.real.project.word.model;

import com.mtsmda.helper.ObjectHelper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by dminzat on 2/28/2017.
 */
public class PartOfSpeech {

    private Integer partOfSpeechId;
    private String partOfSpeech;

    public PartOfSpeech() {

    }

    public PartOfSpeech(Integer partOfSpeechId, String partOfSpeech) {
        this.partOfSpeechId = partOfSpeechId;
        this.partOfSpeech = partOfSpeech;
    }

    public Integer getPartOfSpeechId() {
        return partOfSpeechId;
    }

    public void setPartOfSpeechId(Integer partOfSpeechId) {
        this.partOfSpeechId = partOfSpeechId;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    @Override
    public boolean equals(Object o) {
        if(ObjectHelper.objectIsNull(o)){
            return false;
        }
        if(ObjectHelper.isSameObjects(o, this)){
            return true;
        }
        if(ObjectHelper.isNotEqualsObjects(o.getClass(), this.getClass())){
            return false;
        }
        PartOfSpeech partOfSpeech = (PartOfSpeech) o;
        return new EqualsBuilder()
                .append(this.getPartOfSpeechId(), partOfSpeech.getPartOfSpeechId())
                .append(this.getPartOfSpeech(), partOfSpeech.getPartOfSpeech())
                .isEquals();

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(19, 9)
                .append(this.getPartOfSpeechId())
                .append(this.getPartOfSpeech())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("partOfSpeechId", getPartOfSpeechId())
                .append("partOfSpeech", getPartOfSpeech())
                .toString();
    }

}