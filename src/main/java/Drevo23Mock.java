import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Drevo23Mock<Tip> implements Seznam<Tip> {

    @Override
    public void add(Tip e) {
        throw new OutOfMemoryError();
    }

    @Override
    public Tip removeFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tip remove(Tip e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tip getFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean exists(Tip e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int depth() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String print() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        throw new IOException("Writing the file failed.");
    }

    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        throw new ClassNotFoundException();
    }

    @Override
    public List<Tip> asList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String search(Tip e) {
        throw new UnsupportedOperationException();
    }
}
