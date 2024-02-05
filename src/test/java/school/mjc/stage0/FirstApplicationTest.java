package school.mjc.stage0;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import school.mjc.test.ArgumentResolver;
import school.mjc.test.JavaFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.mjc.parser.Asserts.assertNoClassesExceptTopLevel;
import static school.mjc.parser.Asserts.assertNoImports;
import static school.mjc.parser.Asserts.assertNoInitializationBlocks;
import static school.mjc.parser.Asserts.assertNoMethodsExceptMain;
import static school.mjc.parser.predicate.Dsl.findMain;
import static school.mjc.parser.predicate.Dsl.sout;
import static school.mjc.parser.predicate.Dsl.stringLiteral;

@ExtendWith(ArgumentResolver.class)
public class FirstApplicationTest {

    @Test
    @JavaFileSource("src/main/java/school/mjc/stage0/FirstApplication.java")
    public void verifyOutput(CompilationUnit parsed) {
        MethodDeclaration main = findMain(parsed);

        int helloWorldSouts = main.findAll(MethodCallExpr.class,
                sout().withArgument(stringLiteral("Hello, World!"))).size();
        assertEquals(1, helloWorldSouts,
                "Could not find System.out.println with \"Hello, World!\" argument");
    }

    @Test
    @JavaFileSource("src/main/java/school/mjc/stage0/FirstApplication.java")
    public void verifyNoForbiddenCode(CompilationUnit parsed) {
        assertNoImports(parsed);
        assertNoInitializationBlocks(parsed);
        assertNoMethodsExceptMain(parsed);
        assertNoClassesExceptTopLevel(parsed, "FirstApplication");
    }
}
