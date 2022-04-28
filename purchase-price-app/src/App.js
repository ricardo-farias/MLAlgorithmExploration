

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Purchase Price Predictor</h1>
      </header>
      <div>
        <hr></hr>
        <div className="grid-container">
          <div id="grid-item">
            <div id="content">
              <label>Interior Sqft</label>
              <input></input>
            </div>
          </div>

          <div id="grid-item">
            <div id="content">
              <label>Exterior Sqft</label>
              <input></input>
            </div>
          </div>

          <div id="grid-item">
            <div id="content">
              <label>Total Sqft</label>
              <input></input>
            </div>
          </div>

          <div id="grid-item">
            <div id="content">
              <label>Home Owner Association</label>
              <select>
                <option value="true">Has HOA</option>
                <option value="false">Does not have HOA</option>
              </select>
            </div>
          </div>

          <div id="grid-item">
            <div id="content">
              <label>Number of Bedrooms</label>
              <input></input>
            </div>
          </div>
          
          <div id="grid-item">
            <div id="content">
              <label>Number of Baths</label>
              <input></input>
            </div>
          </div>

          <div id="grid-item">
            <div id="content">
              <label>Driveway</label>
              <select>
                <option value="true">Has a driveway</option>
                <option value="false">Does not have driveway</option>
              </select>
            </div>
          </div>

          <div id="grid-item">
            <div id="content">
              <label>Garage</label>
              <select>
                <option value="true">Has a garage</option>
                <option value="false">Does not have garage</option>
              </select>
            </div>
          </div>

          <div id="grid-item">
            <div id="content">
              <label>Year Built</label>
              <input></input>
            </div>
          </div>

          <div id="grid-item">
            <div id="content">
            </div>
          </div>

          <div id="grid-item">
            <div id="content">
              <button>Submit</button>
            </div>
          </div>
        </div>

        <hr></hr>
        <div className="result-container">
          <div id="prediction-content">
            <h2>$1,000,000</h2>
          </div>
        </div>

      </div>
    </div>
  );
}

export default App;
