import com.ibm.im.custom.props.CompEnvPropsClient
import com.ibm.im.custom.props.PropertiesHelper
import com.urbancode.air.AirPluginTool;
import com.urbancode.air.CommandHelper
import com.urbancode.ud.client.SystemClient;

final airTool = new AirPluginTool(args[0], args[1])
final def props = airTool.getStepProperties()


def udUser = airTool.getAuthTokenUsername()
def udPass = airTool.getAuthToken()
def weburl = System.getenv("AH_WEB_URL")

com.urbancode.air.XTrustProvider.install()

// Load properties
def envProps = props['changedProperties']
def defProps = props['defProperties']
def componentId = props['componentId']
def environmentId = props['environmentId']

// Helper and client initialization
def ph = new PropertiesHelper()
def compEnv = new CompEnvPropsClient(new URI(weburl), udUser, udPass, componentId, environmentId)

// Logic start
def envPropsObj = ph.readString(envProps)
def defPropsObj = ph.readString(defProps)
// - update properties
compEnv.updateProperties(envPropsObj, defPropsObj)

// Logic end