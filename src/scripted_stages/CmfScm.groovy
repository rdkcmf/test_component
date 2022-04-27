package scripted_stages

class CmfScm {
    private final Script script

    CmfScm(Script script) {
        this.script = script
    }
    
    void repo(String url, String manifest='', String branch='rdk-next', String destination='.', Integer depth=0) {
        script.stage('SCM') {
            script.echo "Checkout ${url} using repo"
                /*script.checkout changelog: false, poll: false, \
                    scm: [$class: 'RepoScm', currentBranch: true, depth: "${depth}", \
                        destinationDir: "${destination}", jobs: 1, manifestBranch: "${branch}", \
                        manifestFile: "${manifest}", \
                        manifestRepositoryUrl: "${url}", \
                        quiet: true, resetFirst: true]*/
            script.checkout changelog: false, poll: false, \
                scm: [$class: 'RepoScm', \
                    currentBranch: true, \
                    depth: "${depth}", \
                    destinationDir: "${destination}", \
                    jobs: 1, \
                    manifestBranch: "${branch}", \
                    manifestFile: "${manifest}", \
                    manifestRepositoryUrl: "${url}", \
                    quiet: true, \
                    resetFirst: true \
                ]
        }
    }

    void git(String url, String branch='rdk-next', String destination='.', Integer depth=0, String credentials='') {
        script.stage('SCM') {
            script.echo "Checkout ${url} using git"
            script.checkout changelog: false, poll: false, \
                scm: [ \
                    $class: 'GitSCM', \
                    branches: [[name: "${branch}"]], \
                    extensions: [ \
                        [ \
                            $class: 'CloneOption', \
                            depth: "${depth}", \
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

