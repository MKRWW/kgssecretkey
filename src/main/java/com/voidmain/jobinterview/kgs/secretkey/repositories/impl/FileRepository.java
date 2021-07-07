package com.voidmain.jobinterview.kgs.secretkey.repositories.impl;

import com.voidmain.jobinterview.kgs.secretkey.repositories.IDataRepository;
import org.apache.log4j.Logger;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

/**
 * A simple file based repo
 *
 * @author Markus Kremer
 */
public class FileRepository implements IDataRepository {

    static final Logger LOG = Logger.getLogger(FileRepository.class);

    private URI filePath;

    private static boolean isNotFileAndDoesNotExist(@Nonnull final File file) {
        return !file.exists() || !file.isFile();
    }

    @Override
    public void attach(@Nonnull URI uri, final boolean isSource) throws IOException {
        this.filePath = uri;
        final File sourceFile = new File(this.filePath);
        if (isSource && isNotFileAndDoesNotExist(sourceFile)) {
            throw new IOException("Source does not exist or is a directory");
        }
    }

    @Override
    public void detach() {
        this.filePath = null;
    }

    @Override
    public void save(@CheckForNull byte[] outputBuffer) throws IOException {
        if (null == filePath) {
            throw new IOException("Writing to a detached source is impossible!");
        }
        if (null == outputBuffer) {
            LOG.warn("OOPS: outputBuffer was not provided so we write nothing to destination file");
            return;
        }
        final File outputFile = new File(this.filePath);
        if (outputFile.exists() && outputFile.isFile() && !outputFile.delete()) {
            throw new IOException("OOPS: Could not overwrite destination file!");
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            fileOutputStream.write(outputBuffer);
            fileOutputStream.flush();
            LOG.info("This amount of bytes was written: " + outputBuffer.length);
        } catch (IOException ioException) {
            LOG.error("OOPS: Could not write to file :(.");
            throw ioException;
        }
    }

    @Nonnull
    @Override
    public byte[] read() throws IOException {
        if (null == filePath) {
            throw new IOException("Reading from a detached source is impossible!");
        }
        final File inputFile = new File(this.filePath);
        if (isNotFileAndDoesNotExist(inputFile)) {
            throw new IOException("Source does not exist or is a directory");
        }
        byte[] inputBuffer = new byte[(int) inputFile.length()];
        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            final int bytesRead = inputStream.read(inputBuffer);
            LOG.info("This amount of bytes was read: " + bytesRead);
        } catch (IOException ioException) {
            LOG.error("OOPS: Could not read from file :(.");
            throw ioException;
        }
        return inputBuffer;
    }
}
