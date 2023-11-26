<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>P�gina Web Modificable</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      height: 100vh;
    }

    #sidebar {
      width: 150px;
      background-color: #333;
      color: white;
      padding: 20px;
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    #content {
      flex: 1;
      padding: 20px;
    }

    button {
      margin-bottom: 10px;
      padding: 10px;
      width: 100%;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    #panel {
      margin-top: 20px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }

    th {
      background-color: #333;
      color: white;
    }
  </style>
</head>
<body>

  <div id="sidebar">
    <h2>Acciones</h2>
    <button onclick="loadData('button1')">Bot�n 1</button>
    <button onclick="loadData('button2')">Bot�n 2</button>
    <button onclick="loadData('button3')">Bot�n 3</button>
    <button onclick="loadData('button4')">Bot�n 4</button>
    <button onclick="loadData('button5')">Bot�n 5</button>
    <button onclick="loadData('button6')">Bot�n 6</button>
    <button onclick="loadData('button7')">Bot�n 7</button>
    <button onclick="loadData('button8')">Bot�n 8</button>
    <button onclick="loadData('button9')">Bot�n 9</button>
    <button onclick="loadData('button10')">Bot�n 10</button>
  </div>

  <div id="content">
    <h1>P�gina Web Modificable</h1>
    <div id="panel">
      <h2>Panel de Datos</h2>
      <table id="data-table">
        <thead>
          <tr>
            <th>Columna 1</th>
            <th>Columna 2</th>
            <!-- A�adir m�s columnas seg�n sea necesario -->
          </tr>
        </thead>
        <tbody id="table-body">
          <!-- Aqu� se cargar�n los datos -->
        </tbody>
      </table>
    </div>
  </div>

  <script>
    function loadData(buttonId) {
      // L�gica para cargar datos seg�n el bot�n presionado
      // Puedes hacer una solicitud a una API aqu� y actualizar la tabla con los resultados
      // Por ejemplo, usando fetch() para obtener datos JSON

      // Ejemplo de c�mo actualizar la tabla
      var data = [
        { columna1: 'Dato1', columna2: 'Dato2' },
        { columna1: 'Dato3', columna2: 'Dato4' },
        // A�adir m�s filas seg�n sea necesario
      ];

      var tableBody = document.getElementById('table-body');
      tableBody.innerHTML = '';

      data.forEach(function (row) {
        var newRow = document.createElement('tr');
        newRow.innerHTML = '<td>' + row.columna1 + '</td><td>' + row.columna2 + '</td>';
        tableBody.appendChild(newRow);
      });
    }
  </script>

</body>
</html>
