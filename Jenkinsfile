pipeline {
    agent any

    environment {
        SONAR_HOST_URL = "http://localhost:9000"
        ANDROID_HOME   = "C:\\Users\\PC\\AppData\\Local\\Android\\Sdk"
        JAVA_HOME      = "C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.18.8-hotspot"
        PATH           = "C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.18.8-hotspot\\bin;${env.PATH}"
    }

    stages {

        stage('1 - Checkout') {
            steps {
                git branch: 'feature/ci-cd-pipeline',
                    credentialsId: 'github-credentials',
                    url: 'https://github.com/FatihaKhassil/Endo_project_Devops.git'
                echo "Checkout OK - Build #${BUILD_NUMBER}"
            }
        }

        stage('2 - Build Gradle') {
            steps {
                bat 'gradlew.bat clean assembleDebug --no-daemon'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'app/build/outputs/apk/**/*.apk',
                                     fingerprint: true
                }
            }
        }

        stage('3 - Tests Unitaires') {
            steps {
                bat 'gradlew.bat testDebugUnitTest --no-daemon'
                echo "Tests termines"
            }
            post {
                always {
                    junit testResults: 'app/build/test-results/**/*.xml',
                          allowEmptyResults: true
                }
                failure {
                    echo "ECHEC tests - deploiement bloque"
                }
            }
        }

        stage('4 - Coverage JaCoCo') {
            steps {
                bat 'gradlew.bat jacocoTestReport --no-daemon'
                echo "Rapport JaCoCo genere"
            }
        }

        stage('5 - SonarQube') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    withSonarQubeEnv('SonarQube-Local') {
                        withEnv(["JAVA_HOME=C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.18.8-hotspot",
                                 "PATH=C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.18.8-hotspot\\bin;${env.PATH}"]) {
                            bat """gradlew.bat sonar ^
                                -Dsonar.projectKey=endo-mhealth ^
                                -Dsonar.projectName=Endo-mHealth ^
                                -Dsonar.host.url=http://localhost:9000 ^
                                -Dsonar.token=%SONAR_TOKEN% ^
                                --no-daemon ^
                                --info"""
                        }
                    }
                }
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('6 - Verify APK') {
            steps {
                bat 'dir app\\build\\outputs\\apk\\debug\\'
                echo "APK OK"
            }
        }
    }

    post {
        success { echo "PIPELINE REUSSI - Build #${BUILD_NUMBER}" }
        failure { echo "PIPELINE ECHOUE - Build #${BUILD_NUMBER}" }
        always  { echo "Duree : ${currentBuild.durationString}" }
    }
}