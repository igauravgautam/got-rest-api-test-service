pipeline {
    agent any
    environment {
//         CC = 'clang'
    }
    stages {
        stage('Example') {
//             environment {
//                 DEBUG_FLAGS = '-g'
//             }
            echo $TEST_SECRET
            steps {
                sh 'printenv'
            }
        }
    }
}