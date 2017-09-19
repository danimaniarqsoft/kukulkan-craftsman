/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan.util;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.infotec.dads.kukulkan.engine.domain.core.GeneratedElement;

/**
 * The FileUtil Class is used for common File operations
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    /**
     * Closes this stream and releases any system resources associated with it.
     * If the stream is already closed then invoking this method has no effect.
     *
     * <p>
     * As noted in {@link AutoCloseable#close()}, cases where the close may fail
     * require careful attention. It is strongly advised to relinquish the
     * underlying resources and to internally <em>mark</em> the
     * {@code Closeable} as closed, prior to throwing the {@code IOException}.
     *
     * @throws IOException
     *             if an I/O error occurs
     */
    public static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException ioException) {
                LOGGER.error("The resource actually exist: ", ioException);
            }
        }
    }

    public static Path createPath(String proyectoId, BasePathEnum path, String filePath, String outPutDir) {
        return Paths.get(outPutDir + proyectoId + "/" + path.getPath() + "/" + filePath);
    }

    public static boolean createParentsFileIfNotExist(Path path) {
        if (!Files.exists(path.getParent(), new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
            return createDirectories(path.getParent());
        } else {
            return true;
        }
    }

    public static boolean createDirectories(Path path) {
        try {
            Files.createDirectories(path.getParent());
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public static boolean saveGeneratedElement(GeneratedElement ge){
        FileUtil.createParentsFileIfNotExist(ge.getPath());
    }
}
