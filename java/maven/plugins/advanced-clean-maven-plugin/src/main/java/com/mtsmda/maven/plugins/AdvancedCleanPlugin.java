package com.mtsmda.maven.plugins;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;

/**
 * Created by dminzat on 2/18/2017.
 */
@Mojo(name = "clean")
public class AdvancedCleanPlugin extends AbstractMojo {

    /**
     * File which need to delete
     */
    @Parameter
    private File deleteFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String textException = "deleteFile param ";
        getLog().info("run clean goal");
        if (null == deleteFile) {
            textException += "cannot be null";
            getLog().warn(textException);
            throw new MojoExecutionException(textException);
        }
        if (!deleteFile.exists()) {
            textException += textException + "not exists";
            getLog().warn(textException);
            throw new MojoExecutionException(textException);
        }
        getLog().info("deleteFile - " + deleteFile.getAbsolutePath());
        getLog().info("is " + (deleteFile.isDirectory() ? "directory" : "file"));

        try {
            FileUtils.deleteDirectory(deleteFile);
        } catch (IOException e) {
            getLog().error("exception class is " + e.getClass().getCanonicalName());
            getLog().error("message is " + e.getMessage());
            throw new MojoFailureException("cannot delete file or directory, message " + e.getMessage());
        }
        getLog().info(deleteFile.getAbsolutePath() + " is successfully deleted!");
    }
}