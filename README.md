# SonarAndTrivyService
SonarAndTrivyService to check the code smells and vulnerabilties locally

You need docker installed on your system once docker is up you need to run below command to run the sonarqube container
use command:- docker-compose up -d

For trivy issues you will need to install trivy onto your system using chocolate package
choco install trivy

run below command to create the trivy report
trivy fs --format json --output trivy-report.json

Now for sonar to understand this report you will have to convert above report json file to below format
trivy-sonar.json

{
"issues": []
}

For conversion here's the python script you can use or you can choose some other mechanism
import json

with open("trivy-report.json") as f:
trivy = json.load(f)

issues = []

for result in trivy.get("Results", []):
for vuln in result.get("Vulnerabilities", []):

        issues.append({
            "engineId": "trivy",
            "ruleId": vuln.get("VulnerabilityID"),
            "severity": "CRITICAL" if vuln.get("Severity") == "CRITICAL" else "MAJOR",
            "type": "VULNERABILITY",
            "primaryLocation": {
                "message": f"{vuln.get('PkgName')} - {vuln.get('Title')}",
                "filePath": "pom.xml"
            }
        })

with open("trivy-sonar.json", "w") as f:
json.dump({"issues": issues}, f, indent=2)

print("Trivy issues converted for SonarQube")

finally run below command:-
mvn clean verify sonar:sonar \
-Dsonar.projectKey=SonarAndTrivyService \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login={token can be generated from settings} \
-Dsonar.externalIssuesReportPaths=trivy-sonar.json

for sonar login token you need to go to -> http://localhost:9000/
Left side profile -> security -> create user token.
