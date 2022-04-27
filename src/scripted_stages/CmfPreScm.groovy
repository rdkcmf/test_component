package scripted_stages

class CmfPreScm {
    private final Script script

    CmfPreScm(Script script) {
        this.script = script
    }
    
    void execute(Boolean dirclean=true, String BuildName='') {
        script.stage('Pre SCM') {
            if (dirclean) {
                script.echo "Cleanup previous dir"
                script.cleanWs()
            }
            if (BuildName) {
                script.echo "Set Build Name to ${BuildName}"
                script.buildName "${BuildName} ->"
            }
        }
    }
}


