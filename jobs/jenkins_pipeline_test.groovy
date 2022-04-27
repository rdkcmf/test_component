//library identifier: 'test@master', retriever: modernSCM(
//  [$class: 'GitSCMSource',
//   remote: 'https://code.rdkcentral.com/r/test_component'])
   

//I've added the test_comoponent Library in Jenkins/ManageJenkins
@Library("shared_library_test") _

  
node('ec2-multijob'){
    new scripted.ScriptedFooStage(this).execute('Foo', true)
    stage('stage2') {
        try {
            checkout changelog: false, poll: false, \
                    scm: [$class: 'RepoScm', currentBranch: true, depth: 1, \
                        destinationDir: '.', jobs: 1, manifestBranch: 'rdk-next', \
                        manifestFile: 'cmf-tools.xml', \
                        manifestRepositoryUrl: 'https://code.rdkcentral.com/r/cmf/manifests', \
                        quiet: true, resetFirst: true]
        }
        catch (exc) {
            echo 'Something failed, I should sound the klaxons!'
        }
    } 
}

//pipeline {
//    agent{
//        label 'ec2-multijob'
//    }
//    parameters {
//        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
//        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')
//        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')
//        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three', 'Four'], description: 'Pick something')
//    }
//    options {
//        parallelsAlwaysFailFast()
//        skipDefaultCheckout(true)
//        disableConcurrentBuilds()
//        disableResume()
//        timeout(activity: true, time: 10)
//    }
//    stages {
//        stage('Pre SCM') {
//            steps {
//                buildName '\${BUILD_NUMBER} - a shiny build identifier - blaaah'
//                //cleanWs()
//            }
//        }
//        stage('test') {
//            steps {
//                script {
//                    echo "\n\n\n\n\n\n\n"
//                    def TIMESTAMP = getTimeStamp();
//                    echo "This arrives from shared library: ${TIMESTAMP}"
//                    echo "\n\n\n\n\n\n\n"
//                }
//            }
//        }
//        /* //Not working
//        stages('stage in stages') {
//                stage('instage1'){
//                    steps{
//                        echo 'ici'
//                    }
//                }
//        }*/
//        /* //Not working
//        stage('inti') {
//            steps{
//                scmutils('repo', 'https://code.rdkcentral.com/r/cmf/manifests', 'rdk-next', 'cmf-jenkins-dsl.xml')
//            }
//        }*/
//        /* working OK, but not a shared lib.
//        stage('SCM steps') {
//            steps {
//                checkout changelog: false, poll: false, \
//                    scm: [$class: 'RepoScm', currentBranch: true, depth: 1, \
//                        destinationDir: '.', jobs: 4, manifestBranch: 'rdk-next', \
//                        manifestFile: 'cmf-jenkins-dsl.xml', \
//                        manifestRepositoryUrl: 'https://code.rdkcentral.com/r/cmf/manifests', \
//                        quiet: true, resetFirst: true]
//            }
//        }*/
//    }
//}
