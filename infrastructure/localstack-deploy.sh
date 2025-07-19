#!/bin/bash
set -e  # Exit immediately if any command fails


# 1. Delete previous stack (if it exists)
echo "Deleting existing CloudFormation stack..."
aws --endpoint-url=http://localhost:4566 cloudformation delete-stack \
    --stack-name patient-management || true  # Don't fail if stack doesn't exist

# Optional: wait for stack deletion to complete
echo "Waiting for stack to be deleted..."
aws --endpoint-url=http://localhost:4566 cloudformation wait stack-delete-complete \
    --stack-name patient-management || true  # Don't fail if stack doesn't exist

# 2. Deploy new stack
echo "Deploying new CloudFormation stack..."
aws --endpoint-url=http://localhost:4566 cloudformation deploy \
    --stack-name patient-management \
    --template-file "C:\Users\paris\Desktop\patient-management\infrastructure\cdk.out\localstack.template.json"

# 3. Fetch the load balancer DNS name (if any exists)
echo "Getting Load Balancer DNS name (if applicable)..."
aws --endpoint-url=http://localhost:4566 elbv2 describe-load-balancers \
    --query "LoadBalancers[0].DNSName" --output text || echo "No load balancer found"