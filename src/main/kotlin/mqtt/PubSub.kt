package mqtt

import software.amazon.awssdk.crt.CRT
import software.amazon.awssdk.crt.CrtResource
import software.amazon.awssdk.crt.CrtRuntimeException
import software.amazon.awssdk.crt.mqtt.MqttClientConnectionEvents
import software.amazon.awssdk.iot.AwsIotMqttConnectionBuilder
import java.io.File
import java.util.concurrent.ExecutionException

class PubSub{

    companion object{

        private fun onApplicationFailure(cause: Throwable){
            println("Exception encountered: $cause")
        }

        fun start(){

            val callbacks: MqttClientConnectionEvents = MqttClientConnectionEventsImplementation()

            try {
                /**
                 * Create the MQTT connection from the builder
                 */
//                val builder:AwsIotMqttConnectionBuilder = AwsIotMqttConnectionBuilder.newMtlsBuilderFromPath()


            } catch (ex: CrtRuntimeException){
                onApplicationFailure(ex)
            } catch (ex: InterruptedException){
                onApplicationFailure(ex)
            } catch (ex: ExecutionException){
                onApplicationFailure(ex)
            }

            CrtResource.waitForNoResources()
            println("Complete!")
        }

    }
}



class MqttClientConnectionEventsImplementation : MqttClientConnectionEvents {
    override fun onConnectionInterrupted(errorCode: Int) {
        if(errorCode != 0){
            println("Connection interrupted: $errorCode: ${CRT.awsErrorString(errorCode)}")
        }
    }

    override fun onConnectionResumed(sessionPresent: Boolean) {
        println("Connection resumed: " + (if (sessionPresent) "existing session" else "clean session"))
    }

}