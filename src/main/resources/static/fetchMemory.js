let gaugeChartMemory;
let lineChartMemory;

let memoryUsagePercentage;
let lineMemoryUsagePercentage;

let memoryUsedColor = '#EDEDED';
let memoryFreeColor = '#EDEDED';

// Function to fetch memory data and update the gauge chart
function fetchMemory() {
    fetch('/api/v1/Memory')
        .then((response) => response.json())
        .then((memoryArray) => {
            // Sort the array to get the latest memory record
            memoryArray.sort((a, b) => new Date(a.dateTime) - new Date(b.dateTime));

            // Fetch the latest Memory record (last item after sorting)
            const latestMemory = memoryArray[memoryArray.length - 1];

            // Get the memory usage percentage
            memoryUsagePercentage = latestMemory.memoryUsagePercentage;
            lineMemoryUsagePercentage = latestMemory.memoryUsagePercentage;

            // Clear and update memory list elements
            const memoryListElements = document.querySelectorAll('.memoryList');
            memoryListElements.forEach((memoryListElement) => {
                // Clear existing content
                memoryListElement.innerHTML = ''; 
                // Create a new list item for the latest Memory
                const listMemoryItem = document.createElement('li');
                listMemoryItem.style.whiteSpace = 'pre-line';
                listMemoryItem.textContent =
                    // `Memory ID: ${latestMemory.id}\n` +
                    `Collection cycles:  5sec\n` +
                    `Total Memory: ${latestMemory.totalMemory}  GB\n` +
                    `Free Memory: ${latestMemory.freeMemory}    GB\n` +
                    `Used Memory: ${latestMemory.usedMemory}    GB\n` +
                    `Memory Speed: ${latestMemory.memorySpeed}  MHZ\n` +
                    `Memory Usage Percentage: ${latestMemory.memoryUsagePercentage}%`;
  
                // Append the new list item to the list element
                memoryListElement.appendChild(listMemoryItem);
              });
            
            // Change the used color based on memory usage
            if (memoryUsagePercentage >= 0 && memoryUsagePercentage <= 50) {
                // Set color green
                memoryUsedColor = '#00FF00';
            } else if (memoryUsagePercentage > 50 &&  memoryUsagePercentage <=80){
                // Set color yellow
                memoryUsedColor = '#FFFF00';
            } else if (memoryUsagePercentage > 80 &&  memoryUsagePercentage <=99){
                // Set color red
                memoryUsedColor = '#FF6384'
            } else {
                console.error('MemoryPercentage out of range: ', memoryUsagePercentage);
                return -1;
            }

            // Find the gauge chart element
            const memoryGaugeChartElement = document.getElementById('gaugeChartMemory');
            // Check gauge chart element
            if (memoryGaugeChartElement) {
                // Get the canvas context
                const ctx1Memory = memoryGaugeChartElement.getContext('2d');
                if (!gaugeChartMemory) {
                    // Define data for the gauge chart
                    const memoryGaugetData = {
                        datasets: [
                            {
                                data: [memoryUsagePercentage, 100 - memoryUsagePercentage],
                                backgroundColor: [memoryUsedColor, memoryFreeColor],
                                borderWidth: 0,
                            },
                        ],
                    };
                    // Create the gauge chart if it doesn't exist
                    gaugeChartMemory = new Chart(ctx1Memory, {
                        type: 'doughnut',
                        data: memoryGaugetData,
                        options: {
                            responsive: true,
                            maintainAspectRatio: true,
                            aspectRatio:4,
                            rotation: -90, // Start angle to create a semicircle
                            circumference: 180, // Creates a semicircle
                            cutout: '30%', // Inner cutout for a gauge effect
                            plugins: {
                                legend: { display: false },
                                title: { display: true, text: "Memory Usage Gauge" },
                            },
                        },
                    });
                } else {
                    // Update the existing chart
                    gaugeChartMemory.data.datasets[0].data = [memoryUsagePercentage, 100 - memoryUsagePercentage];
                    gaugeChartMemory.data.datasets[0].backgroundColor = [memoryUsedColor, memoryFreeColor];
                    gaugeChartMemory.update(); // Trigger re-rendering

                }
            } else {
                console.error("Gauge chart element not found.");
            }

            // Find the line chart element
            const memoryLineChartElement = document.getElementById('lineChartMemory');
            if (memoryLineChartElement) {
                // Get the canvns element
                const ctx2Memory = memoryLineChartElement.getContext('2d');
                if (!lineChartMemory) {
                    // Genereate labels:
                    const labels = [ ];

                    for (let i = 1; i<= 10; i++) {
                        labels.push(`${i}`);
                    };

                    // Define data for the line chart
                    const memoryLineData = {
                        labels: labels,
                        datasets: [
                            {
                                label: "Memory Usage",
                                data: [lineMemoryUsagePercentage],
                                fill: false,
                                borderColor: 'rgb(75,192,192)',
                                tension: 0.1
                            },
                        ],
                    };

                    // Create the line chart if it doesn't exist
                    lineChartMemory = new Chart(ctx2Memory, {
                        type: 'line',
                        data: memoryLineData,
                        options: {
                            maintainAspectRatio: true,
                            responsive: true,
                            scales: {
                                y: {
                                    beginAtZero: true,
                                    suggestedMax: 100,
                                },
                            },
                        },
                    });
                } else {
                    // Add new data to the chart
                    lineChartMemory.data.datasets[0].data.push(lineMemoryUsagePercentage);
                    // Remove oldest data if more than 10 items
                    if (lineChartMemory.data.datasets[0].data.length > 10) {
                        // Remove the first item
                        lineChartMemory.data.datasets[0].data.shift();
                    }
                    lineChartMemory.update(); // Trigger re-rendering
                }
            } else {
                console.error("Line chart element not found.");
            }
            
        })

        // Chart.register({
        //     afterDraw: function (chart) {
        //         const ctx = chart.ctx;
        //         const centerX = (chart.chartArea.left + chart.chartArea.right) / 2; // Center X
        //         const centerY = (chart.chartArea.top + chart.chartArea.bottom) / 2; // Center Y
        //         ctx.save(); // Save the current state
        //         ctx.textAlign = 'center';
        //         ctx.textBaseline = 'middle';
        //         ctx.font = 'bold 16px Arial';
        //         ctx.fillText(`${memoryUsagePercentage}%`, centerX, centerY); // Display percentage in the middle
        //         ctx.restore(); // Restore the original state
        //     },
        // })

        .catch(error => console.error('Error fetching Memory data:', error));

        
}

