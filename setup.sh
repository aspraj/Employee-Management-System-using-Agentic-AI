#!/bin/bash

# Employee Management System - Startup Script for Unix/Mac

echo ""
echo "=========================================="
echo "Employee Management System - Startup"
echo "=========================================="
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo "Please install Java 17 or higher"
    exit 1
fi

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed or not in PATH"
    echo "Please install Maven 3.8 or higher"
    exit 1
fi

# Check if Node.js is installed
if ! command -v node &> /dev/null; then
    echo "ERROR: Node.js is not installed or not in PATH"
    echo "Please install Node.js 18 or higher"
    exit 1
fi

echo ""
echo "Step 1: Building Backend..."
echo "=========================================="
cd backend
mvn clean install
if [ $? -ne 0 ]; then
    echo "ERROR: Backend build failed"
    exit 1
fi

echo ""
echo "Step 2: Installing Frontend Dependencies..."
echo "=========================================="
cd ../frontend
npm install
if [ $? -ne 0 ]; then
    echo "ERROR: Frontend dependency installation failed"
    exit 1
fi

echo ""
echo "=========================================="
echo "Setup Complete!"
echo "=========================================="
echo ""
echo "To start the application:"
echo ""
echo "1. Start Backend (in a new terminal):"
echo "   cd backend"
echo "   mvn spring-boot:run"
echo ""
echo "2. Start Frontend (in another new terminal):"
echo "   cd frontend"
echo "   npm start"
echo ""
echo "3. Access the application:"
echo "   http://localhost:4200"
echo ""
echo "Default ports:"
echo "- Backend API: http://localhost:8080/api"
echo "- Frontend: http://localhost:4200"
echo "- H2 Console: http://localhost:8080/h2-console"
echo ""
