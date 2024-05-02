// Fetch data from the Cpu API
function fetchCpu() {
    fetch('/api/v1/Cpu')
    .then((response) => response.json())
    .then((cpuArray) => {
        // Sort the array by 'dateTime' property in ascending order
        cpuArray.sort((a, b) => new Date(a.dateTime) - new Date(b.dateTime));
        // Fetch the latest CPU record (last item after sorting)
        const latestCpu = cpuArray[cpuArray.length - 1];
        // Fetch all CPU list elements
        const cpuListElements = document.querySelectorAll('.cpuList');

        cpuListElements.forEach((cpuListElement) => {
            // Clear existing content
            cpuListElement.innerHTML = '';
            // Create a new list item for the latest CPU
            const listCpuItem = document.createElement('li');
            listCpuItem.textContent = `ID: ${latestCpu.id}, ProcessorType: ${latestCpu.processorType}, ProcessorSpeed: ${latestCpu.processorSpeed}, Count: ${latestCpu.count}, UpTime: ${latestCpu.upTime}`;
            // Append the new list item to the list element
            cpuListElement.appendChild(listCpuItem);
        });
    })
    .catch(error => console.error('Error fetching memory data:', error));
    }