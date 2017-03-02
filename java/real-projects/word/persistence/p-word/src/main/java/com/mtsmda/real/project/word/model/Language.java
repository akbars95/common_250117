package com.mtsmda.real.project.word.model;

import com.mtsmda.helper.ObjectHelper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by dminzat on 2/6/2017.
 */
public class Language {
    private Integer languageId;
    private String languageName;

    public Language() {

    }

    public Language(Integer languageId, String languageName) {
        this.languageId = languageId;
        this.languageName = languageName;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
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
        Language language = (Language) o;
        return new EqualsBuilder()
                .append(this.getLanguageId(), language.getLanguageId())
                .append(this.getLanguageName(), language.getLanguageName())
                .isEquals();

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(19, 9)
                .append(this.getLanguageId())
                .append(this.getLanguageName())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("languageId", languageId)
                .append("languageName", languageName)
                .toString();
    }
}