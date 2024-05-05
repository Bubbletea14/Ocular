// Function to group processes by parent ID
function groupProcessesByParent(processes) {
    const groupedProcesses = {};

    processes.forEach(process => {
        const parentPId = process.parentPId;

        // If the parent ID doesn't exist in the groupedProcesses, initialize it
        if (!groupedProcesses[parentPId]) {
            groupedProcesses[parentPId] = [];
        }

        // Add the current process to the appropriate parent group
        groupedProcesses[parentPId].push(process);
    });

    return groupedProcesses;
}

// Function to display grouped processes in the container
function displayGroupedProcesses(groupedProcesses, container) {
    // Clear the existing content
    container.innerHTML = '';

    // Get all Parent IDs
    const parentPIds = Object.keys(groupedProcesses);
    // Iterate over each group by Parent ID
    for (let i = 0; i < parentPIds.length; i++) {
        // Get the Parent ID
        const parentPId = parentPIds[i];
        // Get the list of processes for this Parent ID
        const processes = groupedProcesses[parentPId];
        // Create a section for each parent ID
        const parentSection = document.createElement('div');
        parentSection.className = 'processGroup';

        // Add a heading to indicate the Parent ID
        const heading = document.createElement('h3');
        heading.textContent = `Parent PID: ${parentPId}`;
        parentSection.appendChild(heading);

        // List for processes
        const processList = document.createElement('ul');

        // Populate the process list for each parent ID
        processes.forEach(process => {
            const listProcessItem = document.createElement('li');
            listProcessItem.textContent = `PID: ${process.pId}, 
            Name: ${process.name}, 
            CPU usage: ${process.cpuPercent}%, 
            Memory usage: ${process.memPercent}%`;

            processList.appendChild(listProcessItem);
        });

        // Add the list to the parent section
        parentSection.appendChild(processList);
        // Add the parent section to the container 
        container.appendChild(parentSection);
    }
}

// Function to fetch and display processes from the API
function fetchProcess() {
    fetch('/api/v1/Processes')
        .then(response => response.json())
        .then(processes => {
            const processListContainer = document.querySelector('.processListContainer');

            // Group the processes by parent ID
            const groupedProcesses = groupProcessesByParent(processes);

            // Display the grouped processes in the container
            displayGroupedProcesses(groupedProcesses, processListContainer);
        })
        .catch(error => console.error('Error fetching processes:', error));
}

// Ensure that the function is called after the DOM has fully loaded
document.addEventListener('DOMContentLoaded', fetchProcess);