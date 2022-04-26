def call(String type = 'repo', String url = '', String branch='', String manifest = '') {
    stage('SCM') {
        steps {
            checkout changelog: false, poll: false, \
                scm: [$class: 'RepoScm', currentBranch: true, depth: 1, \
                    destinationDir: '.', jobs: 4, manifestBranch: $branch, \
                    manifestFile: $manifest, \
                    manifestRepositoryUrl: url, \
                    quiet: true, resetFirst: true]
        }
    }
}

