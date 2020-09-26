package seedu.address.model.student.time;

import java.time.LocalTime;
import java.util.function.Predicate;

import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s dismissal time for Monday is before the query time.
 */
public class MondayDismissalPredicate implements Predicate<Student> {
    private final LocalTime queryTime;

    public MondayDismissalPredicate(LocalTime queryTime) {
        this.queryTime = queryTime;
    }

    @Override
    public boolean test(Student student) {
        return student.getMondayDismissal().dismissalTime.isBefore(queryTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MondayDismissalPredicate // instanceof handles nulls
                && queryTime.equals(((MondayDismissalPredicate) other).queryTime)); // state check
    }
}
