class ScriptedFooStage {
    private final Script script

    ScriptedFooStage(Script script) {
        this.script = script
    }

    // You can pass as many parameters as needed
    void execute(String name, boolean param1) {
        script.stage(name) {
            script.echo "Triggering ${name} stage..."
            script.sh "echo 'Execute your desired bash command here'"

            if (param1) {
                script.sh "echo 'Executing conditional command, because param1 == true'"
            }
        }
    }
}
