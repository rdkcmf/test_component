package scripted_stages

class CmfPreScm {
    private final Script script

    CmfPreScm(Script script) {
        this.script = script
    }
    
    void execute(boolean dirclean=true, String BuildName='') {
        script.stage('Pre SCM') {
            script.echo "${dirclean}"
            if ("${dirclean}") {
                script.echo "Cleanup previous dir"
                script.cleanWs()
            }else{
                script.echo "Not cleaning up directory"
            }
            if ("${BuildName}" != '') {
                script.echo "Set BuildName to ${BuildName}"
                script.buildName "${BuildName} ->"
            }else{
                script.echo "Not setting a custom BuildName"
            }
        }
    }
}


