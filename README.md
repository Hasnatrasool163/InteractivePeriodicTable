# Interactive Periodic Table

A dynamic and educational JavaFX application providing an interactive way to explore the periodic table of elements with rich features for chemistry enthusiasts, students, and educators.

![Periodic Table Screenshot](https://github.com/user-attachments/assets/621bef8b-9bd5-4aab-b757-554292ecce41)


## Features

- **Complete Periodic Table**: Visual representation of all known elements with their basic properties
- **Element Information**: Detailed information about each element including atomic number, mass, group, and period
- **Element States Visualization**: Toggle between normal view and element state visualization (solid, liquid, gas, or unknown/synthetic)
- **Real Element Images**: View actual photographs of elements when available
- **Interactive Element Drag & Drop**: Drag elements to:
    - Element Information Viewer: See details about a specific element
    - Electron Shell Visualizer: View the electron configuration of elements
    - Element Combiner: Experiment with combining elements
- **Search Functionality**: Quickly find elements by name or atomic number
- **Element Comparison**: Compare properties between any two elements
- **Keyboard Shortcuts**:
    - `Ctrl+F`: Quick search
    - `Ctrl+H`: About application

## Screenshots

### Main Interface
![Main Interface](https://github.com/user-attachments/assets/a4ccd5f4-6c9c-4ea9-ac59-93a3df37fca5)


### Element States View
![Element States](https://github.com/user-attachments/assets/3a30efff-bb83-427b-ac36-e0e8155a32b8)


### Element Image View
![Element Image]![images-of-products](https://github.com/user-attachments/assets/e6858614-54a7-4d21-a213-c25207c7e7aa)


### Electron Configuration
![Electron Configuration](https://github.com/user-attachments/assets/0cb9284d-f360-474b-998f-82d9110fd094)


## Requirements

- Java 17 or higher
- JavaFX 22 or higher

## Installation

1. Clone the repository:
```bash
git clone https://github.com/hasnatrasool163/InteractivePeriodicTable.git
```

2. Navigate to the project directory:
```bash
cd InteractivePeriodicTable
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn javafx:run
```

## Usage

### Viewing Element Information
- Click on any element to see basic information
- Right-click on an element to access additional options:
    - Show Details: View comprehensive element information
    - Compare with Another Element: Compare properties of two elements
    - View Element Image: See a real photograph of the element
    - About Application: View application information

### Using Element States Toggle
- Click the "Show States" button to visualize elements by their physical states at room temperature
- The legend at the right indicates the color coding for each state (solid, liquid, gas, unknown/synthetic)
- Click "Hide States" to return to the standard periodic table view

### Electron Shell Visualization
- Drag any element onto the electron shell visualization area to see its electron configuration
- The visualization displays the number of electrons in each shell

### Element Combination
- Drag elements to the combination area to experiment with element combinations
- Clear the combination area with the "Clear" button

### Search
- Enter an element name or atomic number in the search field
- Press Enter or click the "Search" button to highlight the matching element

## Project Structure

```
org.htech.interactiveperiodictable/
├── controller/
│   └── PeriodicTableController.java
├── layout/
│   ├── CompoundFinder.java
│   └── Layouts.java
├── modal/
│   └── Element.java
├── utils/
│   └── UtilityMethods.java
└── resources/
    ├── css/
    │   └── style.css
    ├── icons/
    │   └── periodic.jpeg
    └── images/
        └── [element-images]
```


## Maven Configuration
The project uses Maven for dependency management. Key dependencies include:

<dependencies>
      <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>17.0.6</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>17.0.6</version>
        </dependency>
</dependencies>

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Acknowledgments

-Inspiration from traditional periodic tables with modern interactive elements
