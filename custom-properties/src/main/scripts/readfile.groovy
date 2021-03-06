import com.urbancode.air.AirPluginTool;
import java.nio.file.*;

final airTool = new AirPluginTool(args[0], args[1])
final def props = airTool.getStepProperties()

final def filename = props['filename']
final def charset = props['charset']

byte[] encoded = Files.readAllBytes(Paths.get(filename));
airTool.setOutputProperty('fileContent', new String(encoded, charset));

airTool.storeOutputProperties();
