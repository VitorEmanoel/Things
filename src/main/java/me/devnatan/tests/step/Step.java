package me.devnatan.tests.step;

import java.util.Collection;
import java.util.stream.IntStream;

public class Step<E> {

    private int index = 0;
    private int from = 0;
    private int to = 0;
    private int step = 1;
    private int[] until = {};
    private E[] elements;
    private final AbstractStepConsumer<E> asc = new AbstractStepConsumer<E>() {};

    @SafeVarargs
    public static <E> Step<E> of(E... elements) {
        Step<E> step = new Step<>();
        step.elements = elements;
        return step;
    }

    public static <E> Step<E> of(Collection<E> collection) {
        Step<E> step = new Step<>();
        step.elements = collection.toArray((E[]) new Object[0]);
        return step;
    }

    public Step in(int from, int to) {
        this.from = from;
        this.to = to;
        return this;
    }

    public Step<E> step(int step) {
        this.step = step;
        return this;
    }

    public Step<E> until(int... until) {
        this.until = until;
        return this;
    }

    public Step<E> goTo(int goTo) {
        asc.goTo = goTo;
        return this;
    }

    public final void forEach(StepConsumer<E> stepConsumer) {
        asc.to = to;
        if(asc.goTo > 0) {
            from = asc.goTo;
            asc.goTo = 0;
        } index = asc.index = from;
        for(int i = from; i < to; i++) {
            if(asc.index == to) break; // stop()
            asc.passed = null;
            asc.until = null;
            asc.repeatedStep = false;
            E e = null;

            if(step > 1) {
                i += step - 1;
                asc.step = i;
                asc.element = e = elements[index];
                asc.index = index;
                stepConsumer.consume(asc);
                i = cgoto(i);
                index++;
                asc.originalIndex++;
            } if(elements.length > i) {
                e = elements[i];
                if (!contains(i, until)) asc.passed = e;
                else asc.until = e;
            }

            asc.repeatedStep = asc.step != 0;
            asc.index = index;
            asc.element = e;
            stepConsumer.consume(asc);
            i = cgoto(i);
            index++;
            asc.originalIndex++;
        }
    }

    private int cgoto(int i) {
        if(asc.goTo != 0) {
            from = asc.goTo;
            index = from;
            asc.goTo = 0;
            return index;
        }
        return i;
    }

    private boolean contains(int i, int[] a) {
        return IntStream.of(a).anyMatch(j -> j == i);
    }

    @FunctionalInterface
    public interface StepConsumer<E> {

        void consume(AbstractStepConsumer<E> abstractStepConsumer);

    }

    public static abstract class AbstractStepConsumer<E> {

        public int index;
        public int originalIndex;
        public int step;
        public boolean repeatedStep = false;
        public E passed;
        public E until;
        public E element;
        public int to;
        public int goTo; // mutable

        public String toString() {
            return "AbstractStepConsumer [index=" + index + ", originalIndex=" + originalIndex + ", step=" + step + ", repeatedStep=" + repeatedStep + ", passed=" + passed + ", until=" + until + ", element=" + element + ", goTo=" + goTo + "]";
        }

        public void stop() {
            goTo = 0;
            index = to;
        }

    }

}
