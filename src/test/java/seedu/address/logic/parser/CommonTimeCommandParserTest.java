package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommonTimeCommand;
import seedu.address.model.student.AcademicYearMatchesPredicate;
import seedu.address.model.student.AnyMatchPredicateList;
import seedu.address.model.student.NameContainsKeywordsPredicate;

class CommonTimeCommandParserTest {

    private CommonTimeCommandParser parser = new CommonTimeCommandParser();

    @Test
    public void parse_validArgs_returnsCommonTimeCommand() {
        // no leading and trailing whitespaces
        CommonTimeCommand firstExpectedCommonTimeCommand =
                new CommonTimeCommand(AnyMatchPredicateList.of(
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new AcademicYearMatchesPredicate("123456")
                ));
        assertParseSuccess(parser, " n/Alice Bob ay/123456", firstExpectedCommonTimeCommand);

        // multiple whitespaces between keywords
        CommonTimeCommand secondExpectedCommonTimeCommand =
                new CommonTimeCommand(AnyMatchPredicateList.of(
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new AcademicYearMatchesPredicate("85355255")
                ));
        assertParseSuccess(parser, "  \n  \t n/Alice Bob \t \n \n ay/85355255", secondExpectedCommonTimeCommand);
    }

    @Test
    public void parse_noQuery_failure() {
        assertParseFailure(parser, " ", CommonTimeCommand.MESSAGE_NO_QUERY);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        CommonTimeCommand expectedCommonTimeCommand = new CommonTimeCommand(AnyMatchPredicateList.of(
                new AcademicYearMatchesPredicate("3")
        ));
        assertParseSuccess(parser, " ay/11111111 ay/22222222 ay/3", expectedCommonTimeCommand);
    }
}
