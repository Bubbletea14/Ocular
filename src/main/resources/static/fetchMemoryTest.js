
// Global variable to hold the chart instance
let gaugeChart;
let memoryUsagePercentage;

// Function to fetch memory data and update the gauge chart
function fetchMemory() {
    fetch('/api/v1/Memory')
        .then((response) => response.json())
        .then((memoryArray) => {
            // Sort the array to get the latest memory record
            memoryArray.sort((a, b) => new Date(a.dateTime) - new Date(b.dateTime));
            const latestMemory = memoryArray[memoryArray.length - 1];

            // Get the memory usage percentage
            const memoryUsagePercentage = latestMemory.memoryUsagePercentage;

              // Clear and update memory list elements
              const memoryListElements = document.querySelectorAll('.memoryList');
              memoryListElements.forEach((memoryListElement) => {
                  memoryListElement.innerHTML = ''; // Clear existing content
  
                  // Create a new list item for the latest Memory
                  const listMemoryItem = document.createElement('li');
                  listMemoryItem.style.whiteSpace = 'pre-line';
                  listMemoryItem.textContent =
                      `Memory ID: ${latestMemory.id}\n` +
                      `Total Memory: ${latestMemory.totalMemory}\n` +
                      `Free Memory: ${latestMemory.freeMemory}\n` +
                      `Used Memory: ${latestMemory.usedMemory}\n` +
                      `Memory Speed: ${latestMemory.memorySpeed}\n` +
                      `Memory Usage Percentage: ${latestMemory.memoryUsagePercentage}`;
  
                  // Append the new list item to the list element
                  memoryListElement.appendChild(listMemoryItem);
              });
              
            // Define data for the chart
            const chartData = {
                datasets: [
                    {
                        data: [memoryUsagePercentage, 100 - memoryUsagePercentage], // Used and free memory
                        backgroundColor: ['#FF6384', '#EDEDED'],
                        borderWidth: 0,
                    },
                ],
            };


            // Find the gauge chart element
            const chartElement = document.getElementById('gaugeChart');
            if (chartElement) {
                const ctx = chartElement.getContext('2d'); // Get the canvas context

                if (typeof gaugeChart === "undefined") {
                    // Create the chart if it doesn't exist
                    gaugeChart = new Chart(ctx, {
                        type: 'doughnut',
                        data: chartData,
                        options: {
                            rotation: -90, // Start angle to create a semicircle
                            circumference: 180, // Creates a semicircle
                            cutout: '50%', // Inner cutout for a gauge effect
                            plugins: {
                                legend: { display: false },
                                title: { display: true, text: "Memory Usage Gauge" },
                            },
                        },
                    });
                } else {
                    // Update the existing chart
                    gaugeChart.data.datasets[0].data = [memoryUsagePercentage, 100 - memoryUsagePercentage];
                    gaugeChart.update(); // Trigger re-rendering
                }
            } else {
                console.error("Gauge chart element not found.");
            }
        })

        Chart.register({
            afterDraw: function (chart) {
                const ctx = chart.ctx;
                const centerX = (chart.chartArea.left + chart.chartArea.right) / 2; // Center X
                const centerY = (chart.chartArea.top + chart.chartArea.bottom) / 2; // Center Y
                ctx.save(); // Save the current state
                ctx.textAlign = 'center';
                ctx.textBaseline = 'middle';
                ctx.font = 'bold 16px Arial';
                ctx.fillText(`${memoryUsagePercentage}%`, centerX, centerY); // Display percentage in the middle
                ctx.restore(); // Restore the original state
            },
        })

        .catch((error) => {
            console.error("Error fetching memory data:", error);
        });

        
}

