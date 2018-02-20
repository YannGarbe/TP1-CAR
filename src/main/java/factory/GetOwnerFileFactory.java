package factory;

import java.io.File;
import java.io.IOException;

public class GetOwnerFileFactory {

	public String getOwnerFile(File f) throws IOException {
		return java.nio.file.Files.getOwner(f.toPath()).toString();
	}
}
