package com.mtsmda.real.project.word.rowmapper;

/**
 * Created by dminzat on 3/2/2017.
 */
public class TableAndFieldsName {

    public static class LanguageT{
        public static final String T_LANGUAGES = "T_LANGUAGES";
        public static final String T_LANGUAGES_F_LANGUAGE_ID = "LANGUAGE_ID";
        public static final String T_LANGUAGES_F_LANGUAGE_NAME = "LANGUAGE_NAME";
    }

    public static class PartOfSpeechT{
        public static final String T_PART_OF_SPEECHES = "T_PART_OF_SPEECHES";
        public static final String T_PART_OF_SPEECHES_F_PART_OF_SPEECH_ID = "PART_OF_SPEECH_ID";
        public static final String T_PART_OF_SPEECHES_F_PART_OF_SPEECH = "PART_OF_SPEECH";
    }

    public static class WordT{
        public static final String T_WORDS = "T_WORDS";
        public static final String T_WORDS_F_WORD_ID = "WORD_ID";
        public static final String T_WORDS_F_WORD = "WORD";
        public static final String T_WORDS_F_LANGUAGE_ID = "LANGUAGE_ID";
    }

    public static class WordPartOfSpeechT{
        public static final String T_WORDS_PART_OF_SPEECHES = "T_WORDS_PART_OF_SPEECHES";
        public static final String T_WORDS_PART_OF_SPEECHES_F_PART_OF_SPEECH_WORD_ID = "PART_OF_SPEECH_WORD_ID";
        public static final String T_WORDS_PART_OF_SPEECHES_F_WORD_ID = "WORD_ID";
        public static final String T_WORDS_PART_OF_SPEECHES_F_PART_OF_SPEECH_ID = "PART_OF_SPEECH_ID";
    }

    public static class TranslateT {
        public static final String T_TRANSLATES = "T_TRANSLATES";
        public static final String T_TRANSLATES_F_TRANSLATE_ID = "TRANSLATE_ID";
        public static final String T_TRANSLATES_F_PART_OF_SPEECH_WORD_ID_F = "PART_OF_SPEECH_WORD_ID_F";
        public static final String T_TRANSLATES_F_PART_OF_SPEECH_WORD_ID_S = "PART_OF_SPEECH_WORD_ID_S";
    }

}