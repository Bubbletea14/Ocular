let gaugeChartCpu;
let cpuUsagePercentage;
let cpuUsedColor = '#EDEDED';
let cpuFreeColor = '#EDEDED';

// Fetch data from the Cpu API
function fetchCpu() {
    fetch('/api/v1/Cpu')
        .then((response) => response.json())
        .then((cpuArray) => {
            // Sort the array by 'dateTime' property in ascending order
            cpuArray.sort((a, b) => new Date(a.dateTime) - new Date(b.dateTime));

            // Fetch the latest CPU record (last item after sorting)
            const latestCpu = cpuArray[cpuArray.length - 1];
            
            // Get the Cpu usage percentage
            cpuUsagePercentage = latestCpu.cpuUsagePercentage;

            // Clear and update cpu list elements
            const cpuListElements = document.querySelectorAll('.cpuList');
            cpuListElements.forEach((cpuListElement) => {
                // Clear existing content
                cpuListElement.innerHTML = '';
                // Create a new list item for the latest CPU
                const listCpuItem = document.createElement('li');
                listCpuItem.style.whiteSpace = 'pre-line';
                listCpuItem.textContent = 
                    `CPU ID: ${latestCpu.id}\n` + 
                    `ProcessorType: ${latestCpu.processorType}\n` +
                    `ProcessorSpeed: ${latestCpu.processorSpeed}\n` + 
                    `Count: ${latestCpu.count}\n` +
                    `UpTime: ${latestCpu.upTime}\n`;
                
                
                // Append the new list item to the list element
                cpuListElement.appendChild(listCpuItem);
            });

            // Change the used color based on memory usage
            if (cpuUsagePercentage >= 0 && cpuUsagePercentage <= 50) {
                // Set color green
                cpuUsedColor = '#00FF00';
            } else if (cpuUsagePercentage > 50 &&  cpuUsagePercentage <=80){
                // Set color yellow
                cpuUsedColor = '#FFFF00';
            } else if (cpuUsagePercentage > 80 &&  cpuUsagePercentage <=99){
                // Set color red
                cpuUsedColor = '#FF6384'
            } else {
                console.error('CpuPercentage out of range: ', cpuUsagePercentage);
                // TEST
                cpuUsagePercentage = 20;
                cpuUsedColor = '#00FF00';
                //return -1;
            }

            // Define data for the CPU chart 
            const cpuChartData = {
                datasets: [
                    {
                        data: [cpuUsagePercentage, 100 - cpuUsagePercentage],
                        backgroundColor: [cpuFreeColor, cpuUsedColor],
                        borderWidth: 0,
                    },
                ],
            };

            // Find the gauge chart element
            const chartElement = document.getElementById('gaugeChartCpu');
            if (chartElement){
                const ctx = chartElement.getContext('2d');
                if (!gaugeChartCpu){
                    gaugeChartCpu = new Chart(ctx, {
                        type: 'doughnut',
                        data: cpuChartData,
                        options: {
                            responsive: true,
                            maintainAspectRatio: true,
                            aspectRatio:1,
                            rotation: -90, 
                            circumference: 180, 
                            cutout: '30%', 
                            plugins: {
                                legend: { display: false },
                                title: { display: true, text: "Cpu Usage Gauge" },
                            },
                        },
                    });
                } else {
                    // Update the existing color and chart
                    gaugeChartCpu.data.datasets[0].data = [cpuUsagePercentage, 100 - cpuUsagePercentage];
                    gaugeChartCpu.data.datasets[0].backgroundColor = [cpuUsedColor, cpuFreeColor];
                    gaugeChartCpu.update(); // Trigger re-rendering
                }
            } else {
                console.error('Gauge chart element not found');
            }

        })
        .catch(error => console.error('Error fetching Cpu data:', error));
}