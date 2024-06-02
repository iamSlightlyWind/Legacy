import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        giveAdvice(25, false);
    }

    public static void giveAdvice(int temperature, boolean isRaining) {
        if (temperature > 20) {
            if (isRaining) {
                System.out.println("Take an umbrella.");
            }
        } else {
            System.out.println("Wear a hat.");
        }
    }

    public static void add(String a, String b) {
        try {
            int x = Integer.parseInt(a);
            int y = Integer.parseInt(b);
            System.out.println(x + y);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    public void writeToFile(File file, String content) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(content);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class MyClass {
    private int id;

    public MyClass(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MyClass other = (MyClass) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class Objects {

    public static int hash(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'hash'");
    }
}

class Foo {
    static class Bar implements Comparable<Bar> {
        public int compareTo(Bar rhs) {
            return -1;
        }
    }

    static class FooBar extends Bar {
        @Override
        public int compareTo(Bar rhs) {
            return 0;
        }
    }
}
