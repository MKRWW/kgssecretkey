package com.voidmain.jobinterview.kgs.secretkey.repositories;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * Interface of the Data Repository we need for Input-Output
 *
 * @author Markus Kremer
 */
public interface IDataRepository {

    void attach(@Nonnull final URI uri, final boolean isSource) throws IOException;

    void detach();

    void save(final @CheckForNull byte[] outputBuffer) throws IOException;

    @Nonnull
    byte[] read() throws IOException;

    @Nonnull
    OutputStream getOutPutStream() throws IOException;

    @Nonnull
    InputStream getInputStream() throws IOException;
}
