import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test RandomizedQueue")
class RandomizedQueueTest {
    RandomizedQueue<Object> queue;

    @Test
    @DisplayName("is instantiated with new Deque()")
    void isInstantiatedWithNew() {
        new RandomizedQueue<>();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {
        @BeforeEach
        void createNewRandomizedQueue() {
            queue = new RandomizedQueue<>();
        }

        @Test
        @DisplayName("isEmpty can be true")
        void isEmpty() {
            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("size can be 0")
        void size() {
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("throws IllegalArgumentException when enqueue")
        void throwsExceptionWhenEnqueue() {
            assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
        }

        @Test
        @DisplayName("throws NoSuchElementException when dequeue")
        void throwsExceptionWhenDequeue() {
            assertThrows(NoSuchElementException.class, () -> queue.dequeue());
        }

        @Test
        @DisplayName("throws NoSuchElementException when sample")
        void throwsExceptionWhenSample() {
            assertThrows(NoSuchElementException.class, () -> queue.sample());
        }

        @Nested
        @DisplayName("when new - iterator")
        class WhenNewIterator {
            Iterator<Object> iterator;

            @BeforeEach
            void createRandomizedQueueIterator() {
                iterator = queue.iterator();
            }

            @Test
            @DisplayName("hasNext can be false")
            void hasNext() {
                assertFalse(iterator.hasNext());
            }

            @Test
            @DisplayName("throws NoSuchElementException when next")
            void throwsExceptionWhenNext() {
                assertThrows(NoSuchElementException.class, () -> iterator.next());
            }

            @Test
            @DisplayName("throws UnsupportedOperationException when remove")
            void throwsExceptionWhenRemove() {
                assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
            }
        }

        @Nested
        @DisplayName("after enqueue")
        class AfterEnqueue {
            String anElement = "an element";

            @BeforeEach
            void addFirst() {
                queue.enqueue(anElement);
            }

            @Test
            @DisplayName("isEmpty can be false")
            void isEmpty() {
                assertFalse(queue.isEmpty());
            }

            @Test
            @DisplayName("size can be 1")
            void size() {
                assertEquals(1, queue.size());
            }

            @Nested
            @DisplayName("after enqueue - iterator")
            class AfterEnqueueIterator {
                Iterator<Object> iterator;

                @BeforeEach
                void createRandomizedQueueIterator() {
                    iterator = queue.iterator();
                }

                @Test
                @DisplayName("hasNext can be true")
                void hasNext() {
                    assertTrue(iterator.hasNext());
                }

                @Test
                @DisplayName("next can be anElement")
                void next() {
                    assertEquals(anElement, iterator.next());
                }
            }
        }

        @Nested
        @DisplayName("after add some elements")
        class AfterAddSomeElements {
            String anElement = "an element";
            int numberElements = 20;

            @BeforeEach
            void addSome() {
                for (int i = 0; i < numberElements; i++) {
                    queue.enqueue(anElement);
                }
            }

            @Test
            @DisplayName("isEmpty can be false")
            void isEmpty() {
                assertFalse(queue.isEmpty());
            }

            @Test
            @DisplayName("size can be numberElements")
            void size() {
                assertEquals(numberElements, queue.size());
            }

            @Nested
            @DisplayName("after add some elements - iterator")
            class AfterAddSomeIterator {
                Iterator<Object> iterator;

                @BeforeEach
                void createRandomizedQueueIterator() {
                    iterator = queue.iterator();
                }

                @Test
                @DisplayName("hasNext can be true")
                void hasNext() {
                    assertTrue(iterator.hasNext());
                }

                @Test
                @DisplayName("next can be anElement")
                void next() {
                    assertAll(
                            () -> {
                                String lastElement = "";
                                int i;
                                for (i = 0; iterator.hasNext(); i++) {
                                    lastElement = (String) iterator.next();
                                }
                                assertEquals(anElement, lastElement);
                                assertEquals(numberElements, i);
                            });
                }
            }
        }

        @Nested
        @DisplayName("after add some elements and then remove all")
        class AfterAddSomeAndRemoveAll {
            String anElement = "an element";
            int numberElements = 20;

            @BeforeEach
            void addSome() {
                for (int i = 0; i < numberElements; i++) {
                    queue.enqueue(anElement);
                }
                for (int i = 0; i < numberElements; i++) {
                    queue.dequeue();
                }
            }

            @Test
            @DisplayName("isEmpty can be true")
            void isEmpty() {
                assertTrue(queue.isEmpty());
            }

            @Test
            @DisplayName("size can be 0")
            void size() {
                assertEquals(0, queue.size());
            }

            @Test
            @DisplayName("throws NoSuchElementException when dequeue")
            void throwsExceptionWhenDequeue() {
                assertThrows(NoSuchElementException.class, () -> queue.dequeue());
            }

            @Test
            @DisplayName("throws NoSuchElementException when sample")
            void throwsExceptionWhenSample() {
                assertThrows(NoSuchElementException.class, () -> queue.sample());
            }

            @Nested
            @DisplayName("when add some elements and then remove all - iterator")
            class AfterAddSomeAndRemoveAllIterator {
                Iterator<Object> iterator;

                @BeforeEach
                void createRandomizedQueueIterator() {
                    iterator = queue.iterator();
                }

                @Test
                @DisplayName("hasNext can be false")
                void hasNext() {
                    assertFalse(iterator.hasNext());
                }

                @Test
                @DisplayName("throws NoSuchElementException when next")
                void throwsExceptionWhenNext() {
                    assertThrows(NoSuchElementException.class, () -> iterator.next());
                }
            }
        }
    }
}
