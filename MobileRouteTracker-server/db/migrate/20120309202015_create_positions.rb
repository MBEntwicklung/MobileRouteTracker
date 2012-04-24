class CreatePositions < ActiveRecord::Migration
  def change
    create_table :positions do |t|
      t.references :account
      t.string :longitude
      t.string :latitude
      t.string :altitude
      t.string :accuracy
      t.string :bearing
      t.datetime :time
      t.text :description

      t.timestamps
    end
    add_index :positions, :account_id
  end
end
