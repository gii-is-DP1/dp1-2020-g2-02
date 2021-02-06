package AssertJTest;

import org.assertj.core.api.AbstractAssert;
import org.springframework.samples.petclinic.model.Libro;

public class LibroAssert extends AbstractAssert<LibroAssert, Libro>{
	 
	public LibroAssert(Libro actual) {
	        super(actual, LibroAssert.class);
	    }
	
	public static LibroAssert assertThat(Libro actual) {
	    return new LibroAssert(actual);
	}
	
	public LibroAssert hasFullName(String fullName) {
	    isNotNull();
	    if (!actual.getTitulo().equals(fullName)) {
	        failWithMessage("Expected person to have full name %s but was %s", 
	          fullName, actual.getTitulo());
	    }
	    return this;
	}

}
