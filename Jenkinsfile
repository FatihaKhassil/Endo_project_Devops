// ================================================================
// Jenkinsfile — CI/CD AVANCÉ — Scenario B
// Projet : Endo_project_Devops | com.spmenais.paincare
// Jenkins : localhost:9090 | Branche : feature/ci-cd-pipeline
// ================================================================

pipeline {
    agent any

    environment {
        DOCKER_IMAGE    = "fatihakhassil/endo-mhealth"
        DOCKER_TAG      = "${BUILD_NUMBER}"
        SONAR_HOST_URL  = "http://localhost:9000"
        ANDROID_HOME    = "C:\\Users\\ADMIN\\AppData\\Local\\Android\\Sdk"
    }

    stages {

        // ── Stage 1 : Checkout ────────────────────────────────────
        stage('1 - Checkout') {
            steps {
                git branch: 'feature/ci-cd-pipeline',
                    credentialsId: 'github-credentials',
                    url: 'https://github.com/FatihaKhassil/Endo_project_Devops.git'
                echo "✅ Checkout OK — Commit : ${GIT_COMMIT} — Build avancé #${BUILD_NUMBER}"
            }
        }

        // ── Stage 2 : Build Gradle ────────────────────────────────
        stage('2 - Build Gradle') {
            steps {
                bat 'gradlew.bat clean assembleDebug --no-daemon'
                echo "✅ Build Gradle OK"
            }
            post {
                success {
                    archiveArtifacts artifacts: 'app/build/outputs/apk/**/*.apk',
                                     fingerprint: true
                }
                failure {
                    echo "❌ ECHEC Build Gradle — vérifier les erreurs de compilation"
                }
            }
        }

        // ── Stage 3 : Tests Unitaires ─────────────────────────────
        stage('3 - Tests Unitaires') {
            steps {
                bat 'gradlew.bat testDebugUnitTest --no-daemon'
                echo "✅ Tests unitaires terminés"
            }
            post {
                always {
                    junit testResults: 'app/build/test-results/**/*.xml',
                          allowEmptyResults: true
                }
                success {
                    echo "✅ Tous les tests passent — déploiement autorisé"
                }
                failure {
                    echo "❌ ECHEC tests unitaires — déploiement BLOQUÉ"
                }
            }
        }

        // ── Stage 4 : Coverage JaCoCo ─────────────────────────────
        stage('4 - Coverage JaCoCo') {
            steps {
                bat 'gradlew.bat jacocoTestReport --no-daemon'
                echo "✅ Rapport JaCoCo généré"
            }
            post {
                always {
                    // Publier le rapport HTML si plugin JaCoCo Jenkins installé
                    echo "Rapport disponible : app/build/reports/jacoco/jacocoTestReport/"
                }
            }
        }

        // ── Stage 5 : Analyse SonarQube ──────────────────────────
        stage('5 - Analyse SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube-Local') {
                    bat """gradlew.bat sonar ^
                        -Dsonar.projectKey=endo-mhealth ^
                        -Dsonar.projectName=Endo-mHealth ^
                        -Dsonar.host.url=%SONAR_HOST_URL% ^
                        -Dsonar.token=%SONAR_TOKEN% ^
                        --no-daemon"""
                }
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
                echo "✅ Quality Gate SonarQube passé"
            }
            post {
                failure {
                    echo "❌ ECHEC Quality Gate — corriger les issues SonarQube"
                }
            }
        }
         /*
        // ── Stage 6 : Docker Build & Push ────────────────────────
        stage('6 - Docker Build et Push') {
            steps {
                bat "docker build -t %DOCKER_IMAGE%:%DOCKER_TAG% ."
                bat "docker tag %DOCKER_IMAGE%:%DOCKER_TAG% %DOCKER_IMAGE%:latest"
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS')]) {
                    bat 'echo %DOCKER_PASS%| docker login -u %DOCKER_USER% --password-stdin'
                    bat "docker push %DOCKER_IMAGE%:%DOCKER_TAG%"
                    bat "docker push %DOCKER_IMAGE%:latest"
                }
                echo "✅ Image Docker poussée sur Docker Hub"
            }
            post {
                failure {
                    echo "❌ ECHEC Docker — vérifier que Docker Desktop est ouvert"
                }
            }
        }

        // ── Stage 7 : Verify APK ─────────────────────────────────
        stage('7 - Verify APK') {
            steps {
                bat 'dir app\\build\\outputs\\apk\\debug\\'
                echo "✅ APK présent et archivé"
            }
        }
    }
     */
    post {
        success {
            echo "🎉 PIPELINE AVANCÉ RÉUSSI — Build #${BUILD_NUMBER} — Durée : ${currentBuild.durationString}"
        }
        failure {
            echo "💥 PIPELINE AVANCÉ ÉCHOUÉ — Build #${BUILD_NUMBER} — Voir les logs ci-dessus"
        }
        always {
            echo "📊 Statut final : ${currentBuild.currentResult} — Durée : ${currentBuild.durationString}"
        }
    }
}