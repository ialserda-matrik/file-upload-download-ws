package nl.matrik.trainings.soap.mtom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;

@WebService(endpointInterface = "nl.matrik.trainings.soap.mtom.FileUpload", serviceName = "FileUpload")
public class FileUploadImpl implements FileUpload {

	public void uploadFile(DataHandler attachinfo) {

		try (InputStream input = attachinfo.getInputStream();
				OutputStream output = new FileOutputStream(new File(
						"/Users/ialserda/Documents/Workspaces/Udemy/java-web-services/sections/section-11/mtom/Test.jpg"));) {

			byte[] b = new byte[100000];
			int bytesRead = 0;
			while ((bytesRead = input.read(b)) != -1) {
				output.write(b, 0, bytesRead);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public @XmlMimeType("application/octet-stream")
	DataHandler downloadFile() {
		DataSource source = new FileDataSource(new File("/Users/ialserda/Documents/Workspaces/Udemy/java-web-services/sections/section-11/mtom/Test.jpg"));
		return new DataHandler(source);
	}
}
