package search.solution;

import java.io.Writer;

import io.Write;
import reflect.tag.TaggedObject;
import search.Goal;

public class SolutionWrite<T> extends Write<TaggedObject<T>> {
    protected boolean write_object;

    public SolutionWrite(boolean write_object) {
        this.write_object = write_object;
    }

    @Override
    public void write(TaggedObject<T> sol, Writer out) throws Exception {
        Write.apply(sol.info(Goal.class.getName()), out);
        if (write_object) {
            out.write(' ');
            Write.apply(sol.object(), out);
        }
    }
}