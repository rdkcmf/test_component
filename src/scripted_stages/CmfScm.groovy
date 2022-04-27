package scripted_stages

class CmfScm {
    private final Script script

    CmfScm(Script script) {
        this.script = script
    }
    
    void execute(String url, String manifest='', String branch='rdk-next', String destination='.', Integer depth=0, String credentials='') {
        script.stage('SCM') {
            script.echo "Checkout ${url}"
            if ("${manifest}" != '' ) {
                script.echo "Manifest asked, using repo"
                if ("${credentials}" != '') {
                    script.echo "Credentials can't be used with repo"
                }
                script.checkout changelog: false, poll: false, \
                    scm: [$class: 'RepoScm', currentBranch: true, depth: "${depth}", \
                        destinationDir: "${destination}", jobs: 1, manifestBranch: "${branch}", \
                        manifestFile: "${manifest}", \
                        manifestRepositoryUrl: "${url}", \
                        quiet: true, resetFirst: true]
               /* script.checkout changelog: false, poll: false, \
                    scm: [$class: 'RepoScm', \
                        currentBranch: true, \
                        depth: ${depth}, \
                        destinationDir: "${destination}", \
                        jobs: 1, \
                        manifestBranch: "${branch}", \
                        manifestFile: "${manifest}", \
                        manifestRepositoryUrl: "${url}", \
                        quiet: true, \
                        resetFirst: true \
                    ]*/
            } else {
                script.echo "using git"
                script.checkout changelog: false, poll: false, \
                    scm: [ \
                        $class: 'GitSCM', \
                        branches: [[name: "${branch}"]], \
                        extensions: [ \
                            [ \
                                $class: 'CloneOption', \
                                depth: ${depth}, \
                                noTags: true, \
                                reference: '', \
                                shallow: true \
                            ], \
                            [ \
                                $class: 'RelativeTargetDirectory', \
                                relativeTargetDir: "${destination}" \
                            ] \
                        ], \
                        gitTool: 'Default',\
                        userRemoteConfigs: [[\
                            credentialsId: "${credentials}",\
                            url: "${url}"\
                        ]]\
                    ]
            }
        }
    } 
}

