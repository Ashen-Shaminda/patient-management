#!/bin/bash

set -e # Stops the script if any command fails

set -a
source .env
set +a

if aws --endpoint-url=http://localhost:4566 secretsmanager describe-secret \
    --secret-id jwt-secret >/dev/null 2>&1; then

    aws --endpoint-url=http://localhost:4566 secretsmanager put-secret-value \
        --secret-id jwt-secret \
        --secret-string "$JWT_SECRET"
else
    aws --endpoint-url=http://localhost:4566 secretsmanager create-secret \
        --name jwt-secret \
        --secret-string "$JWT_SECRET"
fi

aws --endpoint-url=http://localhost:4566 cloudformation delete-stack \
    --stack-name patient-management

aws --endpoint-url=http://localhost:4566 cloudformation deploy \
    --stack-name patient-management \
    --template-file "./cdk.out/localstack.template.json"

aws --endpoint-url=http://localhost:4566 elbv2 describe-load-balancers \
    --query "LoadBalancers[0].DNSName" --output text